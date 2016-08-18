package com.company;

import java.util.List;

/**
 * Created by Administrator on 2016-08-18.
 */
public class TailHelper {

    private List<Tail>theTail;

    public TailHelper (List<Tail> theTail){

        this.theTail = theTail;

    }//End constructor

    public int getLastX(){
        return theTail.get(theTail.size()-1).x;
    }
    public int getLastY(){
        return theTail.get(theTail.size()-1).y;
    }

}//End class TailHelper
