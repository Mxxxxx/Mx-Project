package frank.service;

import frank.mapper.RecordMapper;
import frank.model.Record;
import frank.model.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/8/17 22:33
 */
@Service
public class RecordService {


    @Autowired
    private RecordMapper recordMapper;

    public Record query(Integer id) {
        return recordMapper.selectByPrimaryKey(id);
    }

    public List<Record> test(Stu stu) {
        return recordMapper.test(stu);
    }
}
