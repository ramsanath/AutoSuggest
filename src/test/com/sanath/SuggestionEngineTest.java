package com.sanath;

import com.sanath.model.Word;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionEngineTest {

    @Test
    public void should_build_suggestion_engine_successfully() {
        ISuggestionEngine engine = new SuggestionEngine();

        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);
    }

    @Test
    public void should_return_correct_results() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("hel");
        Assert.assertEquals(3, result.size());

        result = engine.search("i");
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void should_return_empty_list_when_no_words_found() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("a");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void should_return_full_word_when_search_second_half() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("hello_world", 10));
                add(new Word("hello", 12));
                add(new Word("hel", 1));
                add(new Word("iel", 1));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("wor");
        String possibleWord = result.get(0).getValue();

        Assert.assertEquals("hello_world", possibleWord);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void should_return_max_10_results() {
        ISuggestionEngine engine = new SuggestionEngine();
        List<Word> words = new ArrayList<Word>() {
            {
                add(new Word("abjured", 11));
                add(new Word("abjurer", 12));
                add(new Word("abjures", 13));
                add(new Word("abaxial", 14));
                add(new Word("abaxile", 15));
                add(new Word("abbotcy", 16));
                add(new Word("abubble", 17));
                add(new Word("abduced", 18));
                add(new Word("abysmal", 19));
                add(new Word("abdomen", 20));
                add(new Word("abduces", 12));
                add(new Word("abducts", 22));
            }
        };
        engine.build(words);

        List<Word> result = engine.search("ab");
        Assert.assertEquals(10, result.size());
    }
}
