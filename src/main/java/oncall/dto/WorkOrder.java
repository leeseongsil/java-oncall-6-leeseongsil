package oncall.dto;

import java.util.List;

public record WorkOrder(List<String> weekdayOrder, List<String> weekendOrder) {
}
