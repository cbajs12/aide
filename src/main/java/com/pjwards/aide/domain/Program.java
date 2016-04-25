package com.pjwards.aide.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pjwards.aide.domain.enums.ProgramType;
import com.pjwards.aide.exception.WrongInputDateException;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Program {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    public static final int MAX_LENGTH_TITLE = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = MAX_LENGTH_TITLE)
    private String title;

    @Lob()
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 5)
    private String begin;

    @Column(nullable = false, length = 5)
    private String end;

    @ManyToOne
    @JoinColumn(name = "program_date_id")
    @JsonBackReference
    private ProgramDate date;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    @JsonBackReference
    private Conference conference;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SPEAKER",
            joinColumns = @JoinColumn(name = "PROGRAM_ID_FRK"),
            inverseJoinColumns = @JoinColumn(name = "SPEAKER_ID_FRK")
    )
    @JsonBackReference
    private Set<User> speakerSet;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramType programType = ProgramType.SESSION;

    public Program() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Program setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Program setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBegin() {
        return begin;
    }

    public Program setBegin(String begin) {
        this.begin = begin;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public Program setEnd(String end) {
        this.end = end;
        return this;
    }

    public ProgramDate getDate() {
        return date;
    }

    public Program setDate(ProgramDate date) {
        this.date = date;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Program setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Conference getConference() {
        return conference;
    }

    public Program setConference(Conference conference) {
        this.conference = conference;
        return this;
    }

    public Set<User> getSpeakerSet() {
        return speakerSet;
    }

    public Program setSpeakerSet(Set<User> speakerSet) {
        this.speakerSet = speakerSet;
        return this;
    }

    public ProgramType getProgramType() {
        return programType;
    }

    public Program setProgramType(ProgramType programType) {
        this.programType = programType;
        return this;
    }

    public void update(Program updated) {
        this.title = updated.title;
        this.description = updated.description;
        this.begin = updated.begin;
        this.end = updated.end;
    }

    public void dateChecker() throws WrongInputDateException {
        this.dateChecker(this.begin, this.end);
    }

    public void dateChecker(String begin, String end) throws WrongInputDateException {
        if (begin == null) {
            throw new WrongInputDateException("Input wrong date, begin is empty.");
        } else if (end == null) {
            throw new WrongInputDateException("Input wrong date, end is empty.");
        }
        LocalTime beginTime = new LocalTime(begin);
        LocalTime endTime = new LocalTime(end);

        Boolean wrongTime = beginTime.isAfter(endTime);

        if (wrongTime) {
            throw new WrongInputDateException(String.format("Input wrong dates begin: %s,end: %s", begin, end));
        }
        LOGGER.debug("Input wrong dates begin: {}, end: {}", begin, end);
    }

    public static class Builder {
        private Program built;

        public Builder(String title, String description, String begin, String end) {
            built = new Program();
            built.title = title;
            built.description = description;
            built.begin = begin;
            built.end = end;
        }

        public Builder(String title, String description) {
            built = new Program();
            built.title = title;
            built.description = description;
        }

        public Program build() {
            return built;
        }

        public Builder conference(Conference conference) {
            built.conference = conference;
            return this;
        }

        public Builder room(Room room) {
            built.room = room;
            return this;
        }

        public Builder date(ProgramDate date) {
            built.date = date;
            return this;
        }

        public Builder speakerSet(Set<User> speakerSet) {
            built.speakerSet = speakerSet;
            return this;
        }

        public Builder programType(ProgramType programType) {
            built.programType = programType;
            return this;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
