import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Meng Xin
 * @Date 2020/8/10 18:29
 * Spring配置框架流程
 * 1.加载配置文件
 * ApplicationContext context = new
 * ClassPathXmlApplicationContext("applications.xml");
 * <p>
 * 2.初始化 根据配置项，进行初始化
 * <bean id="bit" class="java.lang.String">
 * <p>
 * 3.使用 如根容器获取到Bean对象
 * Object bit = context.getBean("bit");
 */
public class Main {
    public static void main(String[] args) {
        //Spring开启容器的方式 ApplicationContext 应用上下文(可以配置管理 Bean对象，及其他工作)
        /*
        ClassPathXmlApplicationContext根据classpath路径，指定一个xml文件（配置文件），并根据
        配置文件，完成配置工作（实例化对象操作）创建一个
         */
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applications.xml");


        Object bit = context.getBean("bit");
        System.out.println(bit.getClass());
        System.out.println(bit);
        System.out.println("============");
        //通过bean的名称获取bean对象，bean的名称就是xml中bean的id
        //如果该类型获取bean有多个对象，会报错
        Object o = context.getBean("myName");

        System.out.println(o.getClass());
        System.out.println(o);
        System.out.println("=========");

        Object duck = context.getBean("duck");
        System.out.println(duck);
        System.out.println("==========");

        Object duck2 = context.getBean("duck2");
        System.out.println(duck2);
    }
}
