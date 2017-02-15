package cubex2.cs4.plugins.vanilla;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cubex2.cs4.api.RecipeInput;
import cubex2.cs4.api.WrappedItemStack;
import net.minecraft.util.ResourceLocation;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapedRecipeTests
{
    private static Gson gson;

    @BeforeClass
    public static void setup()
    {
        gson = new GsonBuilder().registerTypeAdapter(WrappedItemStack.class, new WrappedItemStackDeserializer())
                                .registerTypeAdapter(RecipeInput.class, new RecipeInputDeserializer())
                                .registerTypeAdapter(ResourceLocation.class, new ResourceLocationDeserializer())
                                .registerTypeAdapter(ShapedRecipe.class, ShapedRecipe.DESERIALIZER)
                                .create();
    }

    @Test
    public void testDeserialization()
    {
        ShapedRecipe recipe = gson.fromJson("{ \"shape\": [ \"AA\", \"BB\" ]," +
                                            "\"items\": { \"A\":\"minecraft:stone\", \"B\": { \"item\":\"minecraft:log\" } }," +
                                            "\"result\": \"mincraft:obsidian\"," +
                                            "\"mirrored\": false }", ShapedRecipe.class);

        RecipeInput inputA = recipe.items.get('A');
        RecipeInput inputB = recipe.items.get('B');

        assertArrayEquals(new String[] {"AA", "BB"}, recipe.shape);
        assertTrue(inputA.isItemStack());
        assertTrue(inputB.isItemStack());
        assertNotNull(recipe.result);
        assertFalse(recipe.mirrored);
    }

    @Test
    public void testDeserialization_shapeSingleString()
    {
        ShapedRecipe recipe = gson.fromJson("{ \"shape\": \"BB\" }", ShapedRecipe.class);

        assertArrayEquals(new String[] {"BB"}, recipe.shape);
    }

}
