package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Note;
import com.samonenko.recipeproject.dto.NoteDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteDto implements Converter<Note, NoteDTO> {

    private final RecipeToRecipeDto recipeConverter;

    public NoteToNoteDto(RecipeToRecipeDto recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Override
    public NoteDTO convert(Note note) {
        if (note == null)
            return null;
        NoteDTO noteDto = new NoteDTO();
        noteDto.setId(note.getId());
        noteDto.setRecipe(recipeConverter.convert(note.getRecipe()));
        noteDto.setRecipeNotes(note.getRecipeNotes());
        return noteDto;
    }
}
