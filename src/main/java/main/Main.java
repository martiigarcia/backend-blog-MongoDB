package main;

import api.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import servicios.*;
import web.WebApi;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        String persistenceUnit = "jpamysql";

        VentaService ventaService = new VentaService(persistenceUnit);

        WebApi servicio =
                new WebApi(1234,
                        new ProductoService(persistenceUnit),
                        new PromocionService(persistenceUnit),
                        new VentaService(persistenceUnit),
                        new ClienteService(persistenceUnit),
                        new CategoriaService(persistenceUnit),
                        new MarcaSevice(persistenceUnit));
        servicio.start();

        List<Venta> ventaList = ventaService.ultimasVentas(1L);
        System.out.println(ventaList);
    }
}


//        Gson gson = new Gson();
//        //String json = gson.toJson(ventaList);
////        System.out.println("JSON DE VENTAS PARA PONER EN REDIS: ");
////        System.out.println(json);
//
//        try {
//            JedisPool pool = new JedisPool("localhost", 6379);
//            Jedis jedis = pool.getResource();
//
//            //jedis.hset("user:" + 1, "ventas", json);
//
//            String ventasJson = jedis.hget("user:" + 2, "ventas");
//            if(ventasJson == null){
//                //codigo del servicio
//            }else {
//                Type tipo = new TypeToken<List<Venta>>() {
//                }.getType();
//                List<Venta> lista = gson.fromJson(ventasJson, tipo);
//                System.out.println("VENTAS DEL JSON PARSEADAS A LISTA DE NUEVO: ");
//                System.out.println(lista);
//            }

//            jedis.set("clientName", "Marti");
//            jedis.sadd("planets", "Venus", "Marte", "Tierra");
//            jedis.sadd("planets", "Saturno");
//            System.out.println(jedis.get("clientName"));
//           // System.out.println(jedis.get("planets"));
//            System.out.println(jedis.scard("planets") + " elements");
//            Set<String> planets = jedis.smembers("planets");
//            for (String planet: planets) {
//                System.out.println(planet);
//            }
//
//            jedis.del("students:ggvd");
//
//            // SADD, SREM, SCARD and SMEMBERS
//            jedis.sadd("students:ggvd", "student1", "student2", "student3");
//            jedis.srem("students:ggvd", "student3");
//            System.out.println(jedis.scard("students:ggvd") + " elements");
//            Set<String> students = jedis.smembers("students:ggvd");
//
//            // Print the list
//            for (String student: students) {
//                System.out.println(student);
//            }
//        }catch (Exception e){
//            throw new RuntimeException("Error: "+ e.getMessage());
//        }




