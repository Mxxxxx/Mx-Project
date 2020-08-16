package Mx;

import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/8/12 19:13
 */
public class DuckShop {
    List<Duck> ducks;

    @Override
    public String toString() {
        return "DuckShop{" +
                "ducks=" + ducks +
                '}';
    }

    public List<Duck> getDucks() {
        return ducks;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }
}
