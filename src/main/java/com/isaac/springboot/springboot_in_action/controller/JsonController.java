package com.isaac.springboot.springboot_in_action.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isaac.springboot.springboot_in_action.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("json")
public class JsonController {
    @Autowired
    private ObjectMapper mapper;

    /**
     * 树遍历方式通常适合没有POJO对应的JSON
     *
     * @return
     * @throws Exception
     */
    @GetMapping("readTree")
    public @ResponseBody
    String readTree() throws Exception {
        String json = "{\"name\":\"isaac\",\"id\":10016}";
        JsonNode node = mapper.readTree(json);
        String name = node.get("name").asText();
        int id = node.get("id").asInt();
        return "name:" + name + ",id:" + id;
    }

    /**
     * 对象绑定 使用readValue来反序列化json成一个POJO
     *
     * @return
     * @throws Exception
     */
    @GetMapping("dataBind")
    public @ResponseBody
    String databind() throws Exception {
        String json = "{\"json_name\":\"isaac\",\"id\":10016}";
        User user = mapper.readValue(json, User.class);
        return "name:" + user.getName() + ",id:" + user.getId();
    }

    /**
     * 将POJO对象序列化成json
     *
     * @return
     * @throws Exception
     */
    @GetMapping("serialization")
    public @ResponseBody
    String custom() throws Exception {
        User user = new User();
        user.setName("isaac");
        user.setId("1000865");
        user.setAge(24);
        user.setPhone("130");
        user.setBirthday(new Date());
        String str = mapper.writeValueAsString(user);
        return str;
    }

    @GetMapping("collection")
    public @ResponseBody
    List<User> collection() throws Exception {
        String json = "[" + "{\"json_name\":\"isaac\",\"id\":10016}" + "," + "{\"json_name\":\"isaac2\",\"id\":10016}" + "]";
        JavaType javaType = getCollectionType(List.class, User.class);
        List<User> users = mapper.readValue(json, javaType);
        return users;
    }

    /**
     * 树模型和数据绑定都是基于流式操作完成的，即通过JsonParser类解析JSON，形成JsonToken流，下面的代码是解析JSON
     */
    @GetMapping("parser")
    public @ResponseBody
    String parser() throws Exception {
        String json = "{\"name\":\"isaac\",\"id\":10016}";
        String key = null, value = null;
        JsonFactory f = mapper.getFactory();
        JsonParser parser = f.createParser(json);
        //忽略第一个token START_OBJECT
        JsonToken token = parser.nextToken();
        //"name",FIELD_NAME
        token = parser.nextToken();
        if (token == JsonToken.FIELD_NAME)
            key = parser.currentName();
        token = parser.nextToken();
        //VALUE_STRING
        value = parser.getValueAsString();
        parser.close();
        return key + "," + value;
    }

    private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
