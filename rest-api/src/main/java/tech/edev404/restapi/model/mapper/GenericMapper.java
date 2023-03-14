package tech.edev404.restapi.model.mapper;

public interface GenericMapper <T, O> {
    O map(T t);
}
