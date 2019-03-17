package com.isaac.springboot.springboot_in_action.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @GetMapping("order/{orderId}")
    public Msg getOrderById(@PathVariable String orderId){
        return new Msg("0000","Success get number:" + orderId + " Order");
    }

    @PostMapping("newOrder")
    public Msg createNewOrder(@RequestBody Order order){
        return new Msg("0000","Success create Order order name is " + order.getName());
    }

    private static class Order{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Msg{
        private String msg;
        private String data;

        public Msg(String msg, String data) {
            this.msg = msg;
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
