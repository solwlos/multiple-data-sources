package com.yangs.architecture.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author sol
 * @date 2023/10/23 17:29
 * @Version 1.0
 */
@Data
@TableName("m_user")
public class User {


    @TableId(type = IdType.AUTO)
    private String id;
    private String role_id;
    private String description;
    private String nickname;
    private String username;
    private String password;
    private String realname;
    private String email;
    private String phone;
    private String avatar;
    private String gender;
    private String birthday;
    private String company;
    private  String position;
    private String website;
    private String qq;
    private String wechat;
    private String facebook;
    private String twitter;
    private String youtube;
    private String whatsapp;
    private String tiktok;
    private String instagram;
    private Integer vip_status;
    private Integer vip_level;
    private Timestamp vip_expired_at;
    private Integer rewards_points;
    private BigDecimal rewards_balance;
    private Integer rewards_exp;
    private String tags;
    private Integer language;
    private Timestamp last_login_at;
    private String last_login_ip;
    private Integer download_count;
    private Timestamp last_download_at;
    private String verify_code;
    private Integer certification_status;
    private Integer certification_type;
    private String certification_info;
    private Integer inner_status;
    private String inner_str;
    private Integer inner_value1;
    private Integer inner_value2;
    private Integer inner_value3;
    private Integer inner_value4;
    private Integer inner_value5;
    private Integer type;
    private Integer star;
    private Integer status;

    @TableLogic(value="0",delval="1")
    private Integer is_deleted;
    private Integer version;
    private Timestamp created_at;
    private Timestamp updated_at;
}
