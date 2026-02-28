package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.valueobject.OpeningHours;
import br.com.fiap.restaurant.iam.core.outbound.OpeningHoursOutput;

public class OpeningHoursPresenter {
    private OpeningHoursPresenter() {}

    public static OpeningHoursOutput toOutput(OpeningHours openingHours) {
        return new OpeningHoursOutput(openingHours.getId(), openingHours.getDayOfWeek(), openingHours.getOpenHour(), openingHours.getCloseHour());
    }
}
