package com.demo.spark.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        //assertTrue( true );
    }

    @Test
    public void listSort(){
        List<Integer> list = Arrays.asList(1, 5, 4, 3, 7, 9);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1 +" - " + o2+" = " + (o1-o2));
                return o1-o2;
            }
        });

        System.out.println(list.toString());
    }
}
