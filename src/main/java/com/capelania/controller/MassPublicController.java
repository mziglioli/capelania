package com.capelania.controller;

import com.capelania.form.MassForm;
import com.capelania.model.Mass;
import com.capelania.service.MassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/mass")
public class MassPublicController extends DefaultPublicController<MassService, Mass, MassForm> {

    @Autowired
    MassPublicController(MassService service){
        super(service);
    }

    @GetMapping("/upcoming")
    public List<Mass> findAllUpComing(){
        return service.findAllUpComing();
    }

    @GetMapping("/weekly")
    public List<Mass> findAllWeekly(){
        return service.findAllWeekly();
    }

    @GetMapping("/special")
    public List<Mass> findAllSpecial(){
        return service.findAllSpecial();
    }
}