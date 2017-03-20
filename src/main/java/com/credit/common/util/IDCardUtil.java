package com.credit.common.util;

import com.credit.common.util.time.TimeUtil;

/**
 * Created by wangjunling on 2017/3/14.
 */
public class IDCardUtil
{
	public static Integer getAgeByIDCard(String idCard)
	{
		if (!Validator.isCitizenId(idCard))
		{
			return 0;
		}
		String birthday = idCard.substring(6, 14);
		return TimeUtil.getYearsDifference(TimeUtil.parse(birthday, TimeUtil.FORMAT_YYYYMMDD));
	}

	public static void main(String[] args) {
		Integer ageByIDCard = getAgeByIDCard("41072719930813321X");
		System.out.println(ageByIDCard);
	}
}
