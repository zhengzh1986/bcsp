package com.sf.bcsp.base.entity;


/**
 *  特殊对象 ，用于表示 空对象
 */
public class EmptoyObject {

     private static final EmptoyObject emptyObject = new EmptoyObject();

     public static EmptoyObject newInstantce(){
         return  emptyObject;
     }

}
