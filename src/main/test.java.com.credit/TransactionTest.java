import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.credit.entity.Man;
import com.credit.service.ManService;

/**
 * Created by Michael Chan on 3/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-mvc-dispatcher.xml","classpath:spring/spring-config-jpa.xml"})
public class TransactionTest
{

    private ManService<Man> manService;


    public void setMan(ManService<Man> manService)
    {
        this.manService = manService;
    };

    @Test
    public	void insertfail()                //插入失败要回滚
    {
        Man man = new Man();
        man.setUserId(1000);
        man.setUserName("helo1000");
//        manService.save(man);
        String string = null;
        if(string.equals("")) {
            int i = 0;
        }
      /*  for(int i=0;i<20;i++)
        {
            Man man = new Man();
            man.setUserId(1);
            man.setUserNo(2);
            man.setUserName("xiaoming");
           *//* if(i==10)
            {
                int a=1/0;
            }*//*
            manService.save(man);
        }*/
    }
}
