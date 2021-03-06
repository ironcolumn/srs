package com.srs.domain;

import com.srs.model.SysUser;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static final Menu INSTANCE = new Menu ( );

    public List < MenuItem > getMenus ( SysUser sysUser ) {

        List < MenuItem > menu = new ArrayList <> ( );
        if ( sysUser.getStudent ( ) != null ) {
            menu.add ( new MenuItem ( "学习计划" , "/student/study-plan" , "anticon anticon-appstore-o" ) );
            menu.add ( new MenuItem ( "学生选课" , "/student/course-selection" , "anticon anticon-appstore-o" ) );
            menu.add ( new MenuItem ( "成绩查询" , "/student/score-query" , "anticon anticon-appstore-o" ) );
            //
        } else if ( sysUser.getProfessor ( ) != null ) {
            menu.add ( new MenuItem ( "课程管理" , "/teacher/courses" , "anticon anticon-appstore-o" ) );
            menu.add ( new MenuItem ( "教务排课" , "/teacher/arrange" , "anticon anticon-appstore-o" ) );
            menu.add ( new MenuItem ( "教师选课" , "/teacher/section" , "anticon anticon-appstore-o" ) );
        }
        return menu;
    }

    public static Menu getInstance ( ) {

        return INSTANCE;
    }

    private Menu ( ) {

    }

    public class MenuItem {

        private String text;

        private String link;

        private String icon;

        private MenuItem ( String text , String link , String icon ) {

            this.text = text;
            this.link = link;
            this.icon = icon;
        }

        public String getText ( ) {

            return text;
        }

        public void setText ( String text ) {

            this.text = text;
        }

        public String getLink ( ) {

            return link;
        }

        public void setLink ( String link ) {

            this.link = link;
        }

        public String getIcon ( ) {

            return icon;
        }

        public void setIcon ( String icon ) {

            this.icon = icon;
        }
    }
}


