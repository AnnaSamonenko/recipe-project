package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Note;
import com.samonenko.recipeproject.dto.NoteDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteDtoToNote implements Converter<NoteDTO, Note> {

    private final RecipeDtoToRecipe recipeConverter;

    public NoteDtoToNote(RecipeDtoToRecipe recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Override
    public Note convert(NoteDTO noteDTO) {
        if (noteDTO == null)
            return null;
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setRecipe(recipeConverter.convert(noteDTO.getRecipe()));
        note.setRecipeNotes(noteDTO.getRecipeNotes());
        return note;
    }
}
