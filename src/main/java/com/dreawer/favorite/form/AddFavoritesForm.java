package com.dreawer.favorite.form;


import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.favorite.constants.MessageConstants.*;

@ApiModel(value = "添加收藏夹表单")
public class AddFavoritesForm {

    @Length(min = 1, max = 100, message = VAL_FAVORITES_NAME_LENGTH)
    @NotEmpty(message = VAL_FAVORITES_NAME_NOT_EMPTY)
    private String name = null; // 收藏夹名称

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

    /**
     * 获取属性 <TT>name</TT>（收藏夹名称）的值。
     *
     * @return <TT>name</TT> 收藏夹名称。
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性 <TT>name</TT>（收藏夹名称）的值。
     *
     * @param name 收藏夹名称。
     */
    public void setName(String name) {
        this.name = name.trim();
    }

}
