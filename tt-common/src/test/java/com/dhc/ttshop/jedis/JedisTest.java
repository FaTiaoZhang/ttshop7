package com.dhc.ttshop.jedis;

/**
 * User: DHC
 * Date: 2017/12/1
 * Time: 14:11
 * Version:V1.0
 */
//public class JedisTest {
//
//    //第一种方法：Jedis
//    @Test
//    public void testJedis1(){
//        Jedis jedis = new Jedis("10.31.164.63", 6379);
//        System.out.println(jedis.get("name"));
//        jedis.close();
//    }
//
//    //第二种方法：JedisPool
//    @Test
//    public void testJedis2(){
//        JedisPool jedisPool = new JedisPool("10.31.164.63", 6379);
//        Jedis jedis = jedisPool.getResource();
//        System.out.println("++++++++++++" + jedis.get("name"));
//        jedis.close();
//        jedisPool.close();
//    }
//
//    //第三种方法：集群
//    @Test
//    public void testJedis3(){
//        //创建节点集合
//        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//        //将六个节点存放到集合中
//        nodes.add(new HostAndPort("10.31.164.63", 8001));
//        nodes.add(new HostAndPort("10.31.164.63", 8002));
//        nodes.add(new HostAndPort("10.31.164.63", 8003));
//        nodes.add(new HostAndPort("10.31.164.63", 8004));
//        nodes.add(new HostAndPort("10.31.164.63", 8005));
//        nodes.add(new HostAndPort("10.31.164.63", 8006));
//        //创建jedis集群对象
//        JedisCluster jedisCluster = new JedisCluster(nodes);
//        String name = jedisCluster.get("name");
//        System.out.println("+++++++++++" + name);
//        jedisCluster.close();
//    }
//
//    @Test
//    public void testNotBlank(){
//
//        System.out.println(StrKit.notBlank("                 "));
//    }
//
//
//
//}
