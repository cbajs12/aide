package com.pjwards.aide.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pjwards.aide.domain.enums.Charge;
import com.pjwards.aide.domain.enums.Role;
import com.pjwards.aide.domain.enums.Status;
import com.pjwards.aide.domain.forms.ConferenceForm;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Conference {

    public static final int MAX_LENGTH_NAME = 50;
    public static final int MAX_LENGTH_SLOGAN = 100;
    public static final int MAX_LENGTH_LOCATION = 100;
    public static final int MAX_LENGTH_LOCATION_URL = 255;
    public static final int MAX_LENGTH_DISQUS = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = MAX_LENGTH_NAME)
    private String name;

    @Column(nullable = false, length = MAX_LENGTH_SLOGAN)
    private String slogan;

    @Lob()
    @Column(nullable = false)
    private String description;

    @Column(length = MAX_LENGTH_LOCATION)
    private String location;

    @Column(length = MAX_LENGTH_LOCATION_URL)
    private String locationUrl;

    @Column()
    private double lat;

    @Column()
    private double lan;

    @OneToMany(
            targetEntity = ProgramDate.class,
            mappedBy = "conference",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
//    @JsonIgnore
    @JsonBackReference
    private Set<ProgramDate> programDates;

    @OneToMany(targetEntity = ConferenceRole.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "conference")
    @JsonBackReference
    private Set<ConferenceRole> conferenceRoleSetConference;

    @OneToMany(targetEntity = Presence.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "conference")
    @JsonBackReference
    private Set<Presence> presenceSetConference;

    @ManyToOne
    @JoinColumn(name = "host_id")
    @JsonManagedReference
    private User host;

    @OneToMany(
            targetEntity = Assets.class,
            mappedBy = "conference",
            fetch = FetchType.EAGER
    )
//    @JsonIgnore
    @JsonBackReference
    private Set<Assets> assetsSet;

    @OneToMany(
            targetEntity = Contact.class,
            mappedBy = "conference",
            fetch = FetchType.EAGER
    )
//    @JsonIgnore
    @JsonBackReference
    private Set<Contact> contacts;

    @OneToMany(
            targetEntity = Sponsor.class,
            mappedBy = "conference",
            fetch = FetchType.EAGER
    )
//    @JsonIgnore
    @JsonBackReference
    private Set<Sponsor> sponsors;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PARTICIPANT_CONFERENCE",
            joinColumns = @JoinColumn(name = "CONFERENCE_ID_FRK"),
            inverseJoinColumns = @JoinColumn(name = "PARTICIPANT_ID_FRK")
    )
    @JsonManagedReference
    private Set<User> participants;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Charge charge = Charge.FREE;

    @Column(nullable = false)
    private int price = 0;

    @Lob()
    @Column(length = MAX_LENGTH_DISQUS)
    private String disqus;

    @OneToMany(
            targetEntity = Room.class,
            mappedBy = "conference",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
//    @JsonIgnore
    @JsonBackReference
    private Set<Room> rooms;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Conference setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlogan() {
        return slogan;
    }

    public Conference setSlogan(String slogan) {
        this.slogan = slogan;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Conference setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Conference setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getLocationUrl() {
        if (locationUrl == null || locationUrl.equals("") || locationUrl.startsWith("http://") || locationUrl.startsWith("https://"))
            return locationUrl;
        return "http://" + locationUrl;
    }

    public Conference setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Conference setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLan() {
        return lan;
    }

    public Conference setLan(double lan) {
        this.lan = lan;
        return this;
    }

    public List<ProgramDate> getProgramDates() {
        if (programDates == null) return null;
        List<ProgramDate> list = new ArrayList<>(programDates);
        Collections.sort(list, new ProgramDateCompare());
        return list;
    }

    private class ProgramDateCompare implements Comparator<ProgramDate> {
        @Override
        public int compare(ProgramDate arg0, ProgramDate arg1) {
            return arg0.getDay().compareTo(arg1.getDay());
        }
    }

    public Conference setProgramDates(Set<ProgramDate> programDates) {
        this.programDates = programDates;
        return this;
    }

    public Set<ConferenceRole> getConferenceRoleSetConference() {
        return conferenceRoleSetConference;
    }

    public void setConferenceRoleSetConference(Set<ConferenceRole> conferenceRoleSetConference) {
        this.conferenceRoleSetConference = conferenceRoleSetConference;
    }

    public Set<Presence> getPresenceSetConference() {
        return presenceSetConference;
    }

    public void setPresenceSetConference(Set<Presence> presenceSetConference) {
        this.presenceSetConference = presenceSetConference;
    }

    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    public User getHost() {
        return host;
    }

    public Conference setHost(User host) {
        this.host = host;
        return this;
    }

    public Set<Assets> getAssetsSet() {
        return assetsSet;
    }

    public Conference setAssetsSet(Set<Assets> assetsSet) {
        this.assetsSet = assetsSet;
        return this;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Conference setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Conference setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public Conference setParticipants(Set<User> participants) {
        this.participants = participants;
        return this;
    }

    public Charge getCharge() {
        return charge;
    }

    public Conference setCharge(Charge charge) {
        this.charge = charge;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Conference setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getDisqus() {
        return disqus;
    }

    public void setDisqus(String disqus) {
        this.disqus = disqus;
    }

    public List<Room> getRooms() {
        if (rooms == null) return null;
        List<Room> list = new ArrayList<>(rooms);
        Collections.sort(list, new RoomCompare());
        return list;
    }

    private class RoomCompare implements Comparator<Room> {
        @Override
        public int compare(Room arg0, Room arg1) {
            return arg0.getName().compareTo(arg1.getName());
        }
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean isHost(User user) {
        return host.getId().equals(user.getId());
    }

    public boolean isManager(User user) {
        for (Room room : rooms) {
            if (room.isManager(user)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSpeaker(User user) {
        for (ProgramDate day : programDates) {
            if (day.isSpeaker(user)) {
                return true;
            }
        }

        return false;
    }

    public List<User> getSpeakers() {
        List<User> list = conferenceRoleSetConference.stream().filter(conferenceRole -> conferenceRole.getConferenceRole().equals(Role.SPEAKER)).map(ConferenceRole::getUser).collect(Collectors.toList());

        Collections.sort(list, new UserCompare());
        return list;
    }

    public List<User> getManagers() {
        List<User> list = conferenceRoleSetConference.stream().filter(conferenceRole -> conferenceRole.getConferenceRole().equals(Role.MANAGER)).map(ConferenceRole::getUser).collect(Collectors.toList());

        Collections.sort(list, new UserCompare());
        return list;
    }

    private class UserCompare implements Comparator<User> {
        @Override
        public int compare(User arg0, User arg1) {
            return arg0.getName().compareTo(arg1.getName());
        }
    }

    public Conference() {
    }

    public void update(Conference updated) {
        this.name = updated.name;
        this.slogan = updated.slogan;
        this.description = updated.description;
        this.assetsSet = updated.assetsSet;
        this.location = updated.location;
        this.locationUrl = updated.locationUrl;
        this.lat = updated.lat;
        this.lan = updated.lan;
        this.charge = updated.charge;
        this.price = updated.price;
        this.participants = updated.participants;
    }

    public void update(ConferenceForm form) {
        this.name = form.getName();
        this.slogan = form.getSlogan();
        this.description = form.getDescription();
        this.status = form.getStatus();
        this.charge = form.getCharge();
        this.price = form.getPrice();
        this.host = form.getHost();
        this.location = form.getLocation();
        this.locationUrl = form.getLocationUrl();
        this.disqus = form.getDisqus();
    }

    public static class Builder {
        private Conference built;

        public Builder(String name, String slogan, String description) {
            built = new Conference();
            built.name = name;
            built.slogan = slogan;
            built.description = description;
        }

        public Builder location(String location) {
            built.location = location;
            return this;
        }

        public Builder locationUrl(String locationUrl) {
            built.locationUrl = locationUrl;
            return this;
        }

        public Builder host(User host) {
            built.host = host;
            return this;
        }

        public Builder assets(Set<Assets> assetsSet) {
            built.assetsSet = assetsSet;
            return this;
        }

        public Builder latlan(double lat, double lan) {
            built.lat = lat;
            built.lan = lan;
            return this;
        }

        public Builder contacts(Set<Contact> contacts) {
            built.contacts = contacts;
            return this;
        }

        public Builder status(Status status) {
            built.status = status;
            return this;
        }

        public Builder charge(Charge charge) {
            built.charge = charge;
            return this;
        }

        public Builder price(int price) {
            built.price = price;
            return this;
        }

        public Builder disqus(String disqus) {
            built.disqus = disqus;
            return this;
        }

        public Builder sponsor(Set<Sponsor> sponsors) {
            built.sponsors = sponsors;
            return this;
        }

        public Conference build() {
            return built;
        }
    }

    /*@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }*/
}
