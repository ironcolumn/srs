package com.srs.controller;

import com.srs.bind.CurrentUser;
import com.srs.po.Section;
import com.srs.po.SysUser;
import com.srs.service.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping (value = "sections", produces = { APPLICATION_JSON_UTF8_VALUE})
public class SectionController {

    @Autowired
    SectionService sectionService;

    @GetMapping (value = "")
    public ResponseEntity getAllSections ( ) {
        return ResponseEntity.ok (sectionService.findAll ( ) );
    }

    @GetMapping (value = "my")
    public ResponseEntity getMySections ( @CurrentUser SysUser sysUser ) {

        return ResponseEntity.ok (sectionService.findMySections (sysUser ) );
    }

    @GetMapping ("teacher/available")
    public ResponseEntity getTeacherAvailableSections ( ) {
        return ResponseEntity.ok (sectionService.getTeacherSectionAvailable ( ) );
    }

    @GetMapping (value = "student/available")
    public ResponseEntity getStudentAvailableSections ( ){
        return ResponseEntity.ok (sectionService.getStudentSectionAvailable ( ) );
    }

    @PostMapping (value = "choose")
    public ResponseEntity chooseSection ( @CurrentUser SysUser sysUser, @RequestBody Section section ) {
        return ResponseEntity.ok (sectionService.chooseOneSection (sysUser.getProfessor ( ), section ) );
    }

    @PostMapping (value = "")
    public ResponseEntity saveOneSection ( @RequestBody Section section ) {
        return ResponseEntity.ok (sectionService.saveOne (section ) );
    }

    @DeleteMapping (value = "{id}")
    public ResponseEntity deleteOneSection ( @PathVariable Integer id ) {
        return ResponseEntity.ok (sectionService.deleteOneById (id ) );
    }

    @DeleteMapping (value = "my/{id}")
    public ResponseEntity deleteOneSelectedSection ( @PathVariable Integer id ) {
        return ResponseEntity.ok (sectionService.unChooseOneSection (id ) );
    }

}
