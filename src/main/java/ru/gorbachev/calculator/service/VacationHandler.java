package ru.gorbachev.calculator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VacationHandler {
    private static final Set<LocalDate> holidays = new HashSet<>();
    private static final Set<Integer> years = new HashSet<>();

    public VacationHandler() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1.2");
            ctx.init(null, null, null);
            SSLContext.setDefault(ctx);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumberOfWorkDays(LocalDate start, int amount) {
        List<LocalDate> dates = start.datesUntil(start.plusDays(amount))
                .filter(x -> x.getDayOfWeek().getValue() < 6)
                .filter(x -> !holidays.contains(x))
                .collect(Collectors.toList());
        lookForUnsavedYears(dates);
        return (int) dates.stream()
                .filter(x -> !holidays.contains(x))
                .count();
    }

    private void lookForUnsavedYears(List<LocalDate> dates) {
        for (LocalDate date : dates)
            if (!years.contains(date.getYear())) {
                int year = date.getYear();
                loadNewHolidays(year);
                years.add(year);
            }
    }

    private void loadNewHolidays(int year) {
        String uri = "https://date.nager.at/api/v3/PublicHolidays/" + year + "/RU";
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonArray = objectMapper.readTree(res);
            for (JsonNode node : jsonArray)
                holidays.add(LocalDate.parse(node.path("date").asText()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
