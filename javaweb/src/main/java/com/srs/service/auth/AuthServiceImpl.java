package com.srs.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.srs.domain.Menu;
import com.srs.po.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final Menu menu = Menu.getInstance ( );

    @Override
    public String init ( SysUser sysUser ) {

        String       userName = "";
        ObjectMapper mapper   = new ObjectMapper ( );
        ObjectNode   res      = mapper.createObjectNode ( );
        if ( sysUser.getStudent ( ) != null ) {
            userName = sysUser.getStudent ( ).getName ( );
        } else if ( sysUser.getProfessor ( ) != null ) {
            userName = sysUser.getProfessor ( ).getName ( );
        }
        List < Menu.MenuItem > menus = menu.getMenus ( sysUser );
        res.put ( "user" , userName );
        res.set ( "menu" , mapper.valueToTree ( menus ) );
        return res.toString ( );
    }
}
