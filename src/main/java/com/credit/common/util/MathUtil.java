package com.credit.common.util;

/**
 * Created by wangjunling on 2017/3/16.
 */
public class MathUtil
{
	public static double getStandardDevition(Long[] longs,double avg)
	{

		double square = 0;
		for (Long i : longs)
		{
			square += Math.pow(((double) i - avg), 2);
		}
		return Math.sqrt(square / (longs.length - 1));

	}

	public static void main(String[] args)
	{
		Long[] a = { 5L, 6L, 8L, 9L};
		System.out.println(getStandardDevition(a,7));
	}
}
