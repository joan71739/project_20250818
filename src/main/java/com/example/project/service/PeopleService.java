package com.example.project.service;

import com.example.project.model.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PeopleService {

    static List<People> list =new ArrayList<People>();

    static {
        list.add(new People("1", "John Doe"));
        list.add(new People("2", "John Joanne"));
        list.add(new People("3", "John Mark"));
        list.add(new People("4", "John Mary"));
    }

    public List<People> getAllPeople() {
        return list;
    }

    public boolean deletePeopleById(String id) {
        return list.removeIf(people -> people.getId().equals(id));
    }

    public People addPeople(People people) {
        int maxId=list.stream().mapToInt(p -> Integer.parseInt(p.getId()))
                .max()
                .orElse(0);
        String newId=String.valueOf(maxId +1);
        People newPeople=new People(newId,people.getName());
        list.add(newPeople);
        return newPeople;
    }

    public People updatePeople(String id, People people) {
        People target = list.stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(!Objects.isNull(target)){
            target.setName(people.getName());
            return target;
        }
        return null;

    }
}
