package work.lince.predict.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.lince.commons.authentication.AuthenticationService;
import work.lince.predict.model.Analyze;
import work.lince.predict.model.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class AnalyzeService {

    @Autowired
    protected DateService dateService;

    @Autowired
    protected AuthenticationService authenticationService;

    public Analyze create(Analyze analyze) {
        analyze.setId(UUID.randomUUID().toString());
        analyze.setUser(authenticationService.getAuthenticatedUser());
        analyze.setCreate(dateService.offsetDateTimeNow());
        return analyze(analyze);
    }

    protected Analyze analyze(Analyze analyze) {
        SimpleRegression simpleRegression = new SimpleRegression(true);
        analyze.getSample().forEach(point -> addSamplePoint(simpleRegression, point));
        analyze.getPredict().forEach(point -> predictPoint(simpleRegression, point));
        analyze.setDetails(getDetails(simpleRegression));
        return analyze;
    }

    protected Map<String, Object> getDetails(SimpleRegression regression) {
        Map<String, Object> details = new HashMap();
        details.put("intercept", regression.getIntercept());
        details.put("r", regression.getR());
        details.put("intercept", regression.getIntercept());
        details.put("intercept", regression.getIntercept());
        details.put("intercept", regression.getIntercept());
        return details;
    }

    protected void addSamplePoint(SimpleRegression regression, Point point) {
        regression.addData(point.getX(), point.getY());
    }

    protected void predictPoint(SimpleRegression regression, Point point) {
        point.setY(regression.predict(point.getX()));
    }

}