package com.finest.zhy.comm;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by kezy on 2020/4/15.
 */
public class PropertyFee{

    /**
     *  假设每个人卡里的余额
     **/
    private  static double userA = 100.1, userB = 200.2, userC=300.3, userD =400.4;

    /**
     *  每个人需要支出的费用
     **/
    private static double moneyA, moneyB, moneyC, moneyD;

    /**
     *  人数
     **/
    private static int COUNT = 4;

    public static void main(String[] args){

         PathMatcher antPathMatcher = new AntPathMatcher();

         System.out.println(antPathMatcher.isPattern("/user/001"));// 返回 false
         System.out.println(antPathMatcher.isPattern("/user/*")); // 返回 true
         System.out.println(antPathMatcher.match("/user/001","/user/**"));// 返回 true
         System.out.println(antPathMatcher.match("/resources-interface/resource/*","/resources-interface/resource/userList"));// 返回 true


//        System.out.println("请输入物业费：");
//        Scanner sc = new Scanner(System.in);
//        long money = sc.nextLong(); //物业费金额
//        calculation(money,COUNT);
    }


    public static void calculation(double money, int count){
        double total = money;
        double average = Math.round(total / count);
        if (money ==0 || count == 0){
            System.out.println("用户A支付的费用是：" + moneyA + ",剩余费用：" + userA );
            System.out.println("用户B支付的费用是：" + moneyB + ",剩余费用：" + userB );
            System.out.println("用户C支付的费用是：" + moneyC + ",剩余费用：" + userC );
            System.out.println("用户D支付的费用是：" + moneyD + ",剩余费用：" + userD );
            if(money>0){
                System.out.println("当前房间欠费，欠费金额为：" + money);
            }else{
                System.out.println("当前房间不欠费");
            }
            return ;
        }
        money=0;
        if(userA>0){
            if (average > userA ){
                moneyA = userA+moneyA;
                money = average- userA;
                COUNT --;
                userA = 0;
            } else  {
                moneyA = average+moneyA;
                userA = userA - average;
            }
        }
       if(userB>0){
           if (average > userB){
               moneyB = userB+moneyB;
               money = average- userB + money;
               COUNT --;
               userB = 0;
           } else {
               moneyB = average+moneyB;
               userB = userB - average;
           }
       }
       if(userC>0){
           if (average > userC){
               moneyC = userC+moneyC;
               money = average- userC + money;
               COUNT --;
               userC = 0;
           } else {
               moneyC = average+moneyC;
               userC = userC - average;
           }
       }
       if(userD>0){
           if (average > userD ){
               moneyD = userD+moneyD;
               money = average- userD + money;
               COUNT --;
               userD=0;
           } else {
               moneyD = average+moneyD;
               userD = userD - average;
           }
        }
        calculation(money,COUNT);
    }
}