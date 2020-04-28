package work.lince.project.controller;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.commons.log.LogExecutionTime;
import work.lince.project.service.ProjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LogExecutionTime
@RestController
@RequestMapping(path = "/math")
public class MathController {

    @Autowired
    protected ProjectService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@RequestBody @Validated Map<String, Object> body) {
        SimpleRegression simpleRegression = new SimpleRegression(true);
        List<Map<String, Double>> points = (List<Map<String, Double>>) body.get("points");
        List<Double> predictArray = (List<Double>) body.get("predict");

        for (Map<String, Double> point : points) {
            simpleRegression.addData(point.get("x"), point.get("y"));
        }

        List<Map<String, Double>> result = new ArrayList();
        for (Double x : predictArray) {
            Map<String, Double> point = new HashMap();
            point.put("x", x);
            point.put("y", simpleRegression.predict(x));
            result.add(point);
        }
        body.put("result", result);
        return body;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> findAll() {
        SimpleRegression simpleRegression = new SimpleRegression(true);
        simpleRegression.addData(new double[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {5, 6}
        });

        Map<String, Object> result = new HashMap();
        result.put("slope", simpleRegression.getSlope());
        result.put("intercept", simpleRegression.getIntercept());
        result.put("prediction for 1.5", simpleRegression.predict(1.5));
        result.put("prediction for 2", simpleRegression.predict(2));
        result.put("prediction for 3", simpleRegression.predict(3));
        result.put("prediction for 6", simpleRegression.predict(6));
        result.put("prediction for 7", simpleRegression.predict(7));
        result.put("prediction for 8", simpleRegression.predict(8));
        return result;
    }


}