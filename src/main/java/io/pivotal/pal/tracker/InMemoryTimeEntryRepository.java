package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    private Long currentId = 1L;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return timeEntries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {

        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long newId, TimeEntry timeEntry) {

        if (find(newId) == null) return null;
        TimeEntry updatedTimeEntry = new TimeEntry(
                newId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.replace(newId, updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(long id) {

        timeEntries.remove(id);
    }

}
