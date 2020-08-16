package Mx;

/**
 * @Author Meng Xin
 * @Date 2020/8/10 20:54
 */
/*
duck 名字分别为：北京烤鸭,2->甜皮鸭,1>酱鸭,3
 */

public class Duck3 {
    private String name;
    private Integer age;
    private Duck3 next;

    public Duck3 getNext() {
        return next;
    }

    public void setNext(Duck3 next) {
        this.next = next;
    }

    public Duck3() {
    }

    @Override
    public String toString() {
        return "Duck3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", next=" + next +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
