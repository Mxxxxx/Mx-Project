package frank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import frank.model.Record;
import frank.model.Stu;
import frank.model.User;
import frank.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Meng Xin
 * @Date 2020/8/17 22:27
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/query")
    public Object query(Integer id) {//Controller-->>Service-->>Mapper
        Record record = recordService.query(id);
        return record;
    }

    @PostMapping("/test")
    public Object test(Stu stu) {
        List<Record> records = recordService.test(stu);
        return records;
    }
}
