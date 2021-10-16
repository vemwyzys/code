package cn.gzho.esdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-06 10:47 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JDGoodInfo {

    private Long id;

    private String title;

    private String img;

    private double price;
}
