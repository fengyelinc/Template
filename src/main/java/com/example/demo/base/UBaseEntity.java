package com.example.demo.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class UBaseEntity<T extends Model<?>> extends Model<T>  {

    /**
     * 实体编号（唯一标识）
     */
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    protected String id;

    /**
     *  创建者
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    protected String creator;


    /**
     * 创建日期
     */
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    protected Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    protected String updater;

    /**
     * 更新日期
     */
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;



    /**
     * 逻辑删除（Y：正常；N：删除；A：审核；）
     */
    @TableField(value = "isDel")
    protected Boolean isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (null == obj) {
//            return false;
//        }
//        if (this == obj) {
//            return true;
//        }
//        if (!getClass().equals(obj.getClass())) {
//            return false;
//        }
//        UBaseEntity<?> that = (UBaseEntity<?>) obj;
//        return null != this.getId() && this.getId().equals(that.getId());
//    }


}
