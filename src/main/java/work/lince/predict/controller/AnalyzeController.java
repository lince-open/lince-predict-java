package work.lince.predict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.commons.log.LogExecutionTime;
import work.lince.predict.model.Analyze;
import work.lince.predict.service.AnalyzeService;

@LogExecutionTime
@RestController
@RequestMapping(path = "/analysis")
public class AnalyzeController {

    @Autowired
    protected AnalyzeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Analyze create(@RequestBody @Validated Analyze body) {
        return service.create(body);
    }


}