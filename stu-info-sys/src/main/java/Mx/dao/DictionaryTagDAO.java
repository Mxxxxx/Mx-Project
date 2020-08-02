package Mx.dao;

import Mx.model.DictionaryTag;
import Mx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/7/31 19:42
 */
public class DictionaryTagDAO {
    public static List<DictionaryTag> query(String key) {
        //jdbc查询
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        List<DictionaryTag> tags = new ArrayList<>();
        try {
            //1.获取数据库连接dictionary
            c = DBUtil.getConnection();
            //2.创建操作命令对象
            String sql = "select concat(d.dictionary_key, dt.dictionary_tag_key) dictionary_tag_key, " +
                    "       dt.dictionary_tag_value " +
                    "from dictionary d " +
                    "         join dictionary_tag dt on d.id = dt.dictionary_id " +
                    "where d.dictionary_key = ?";
            ps = c.prepareStatement(sql);
            //4.执行sql语句
            ps.setString(1, key);
            r = ps.executeQuery();
            //4.查询结果集
            while (r.next()) {
                DictionaryTag tag = new DictionaryTag();
                //设置属性，通过结果集获取，并设置
                tag.setDictionaryTagKey(r.getString("dictionary_tag_key"));
                tag.setDictionaryTagValue(r.getString("dictionary_tag_value"));
                tags.add(tag);
            }
            return tags;
        } catch (Exception e) {
            throw new RuntimeException("查询数据字典标签出错", e);
        } finally {
            DBUtil.close(c, ps, r);
        }
    }
}
