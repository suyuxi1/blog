package com.scs.web.blog.domain.dto;
import lombok.Data;

import java.util.List;
/**
 * @author suyuxi
 * @className Province
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@Data
public class Province {
    private String name;
    private String level;
    private String code;
    private List<City> cities;
}