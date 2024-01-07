package com.example.restapi.ServiceImpl;

import com.example.restapi.DTO.CustomerResponse;
import com.example.restapi.DTO.OrderRequest;
import com.example.restapi.DTO.OrderResponse;
import com.example.restapi.Error.*;
import com.example.restapi.Model.Customer;
import com.example.restapi.Model.Orders;
import com.example.restapi.Model.Product;
import com.example.restapi.Repository.CustomerRepository;
import com.example.restapi.Repository.OrderRepository;
import com.example.restapi.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class OrderImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerImpl customerImpl;

    public int calculateTotalCountAndUpdateProductTable(List<Product> productList)
    {
        for(Product product:productList)
        {
            if(product.getIsAvailable()==0 || product.getAvailableCount()<product.getLastSoldCount())
                throw new ProductNotAvailable("Sorry! your product is not available",product.getName());
            System.out.println("Product===="+product.getName());
            System.out.println("soldCount====="+product.getLastSoldCount());
            System.out.println("available count ======"+product.getAvailableCount());
            product.setAvailableCount(product.getAvailableCount()-product.getLastSoldCount());
            product.setTotalSoldCount(product.getTotalSoldCount()+product.getLastSoldCount());
        }

        int amount =0;
        for (Product product : productList) {
            System.out.println("Scccccccccccccccccccc"+product.getLastSoldCount());
            amount += (product.getLastSoldCount()) * (product.getPrize());
        }
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+amount);
        return amount;
    }
    public OrderResponse placeOrder(Orders orders)
    {
//-----------------------setting the customer-----------------------------
        Customer customer= customerImpl.getCustomer(orders.getCustomer().getCustomerId());
        orders.setCustomer(customer);
        //----------------getting and setting the products----------------------------
       List<Product> createdProductList=new ArrayList<>();
        for(int i=0; i<orders.getProductList().size();i++)
        {
            Product product=productRepository.findById(orders.getProductList().get(i).getProductId()).orElseThrow(() ->new ProductNotAvailable("No product with such id is availavle","try with new product"));
            product.setLastSoldCount(orders.getProductList().get(i).getLastSoldCount());
            createdProductList.add(i,product);
        }
        orders.setTotalAmount(calculateTotalCountAndUpdateProductTable(createdProductList));
        return orderToOrderResponse(orderRepository.save(orders));
    }



//    public Orders placeOrder(OrderRequest orderRequest)
//    {
//        Orders orders=new Orders();
//        orders.setCustomer(customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("No Customer with such id")));
////        orders.setProductList(orderRequest.getProductAndCount().stream());
//        Stream<Map<Integer,Integer>> mapStream=orderRequest.getProductAndCount().entrySet().stream()
//            .filter(productRepository.findById(n->).isPresent())
//        return orders;
//    }

    public List<OrderResponse> getAllOrders()
    {
        return  orderRepository.findAll().stream()
                .map(this::orderToOrderResponse)
                .toList()
                ;
    }
    public OrderResponse getOrderById(int id)
    {
        return  orderToOrderResponse(orderRepository.findById(id).get());
    }
    public List<OrderResponse> getOrdersByAmountGreaterThan(int amount)
    {
        return Optional.of(orderRepository.findByTotalAmountGreaterThan(amount))
                .filter(orders -> !orders.isEmpty())
                .orElseThrow(() -> new NoSuchOrder("No Orders Found","Try with smaller amount"))
                .stream()
                .map(this::orderToOrderResponse)
                .toList();
    }
    public List<OrderResponse> getOrdersAmountLessThan(int amount)
    {
        return Optional.of(orderRepository.findByTotalAmountLessThan(amount))
                .filter(orders -> !orders.isEmpty())
                .orElseThrow(()->new NoSuchOrder("No order with the given limit",
                        "increase the amount value and try again"))
                .stream()
                .map(this::orderToOrderResponse)
                .toList();
    }


    private OrderResponse orderToOrderResponse(Orders orders)
    {
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderId(orders.getOrderId());
        orderResponse.setAmount(orders.getTotalAmount());
        orderResponse.setProducts(orders.getProductList()
                .stream()
                .map(Product::getName).
                toList());

        return orderResponse;
    }
}
