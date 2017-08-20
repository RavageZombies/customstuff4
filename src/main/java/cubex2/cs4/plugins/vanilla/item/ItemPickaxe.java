package cubex2.cs4.plugins.vanilla.item;

import cubex2.cs4.plugins.vanilla.ContentItemPickaxe;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ItemPickaxe extends net.minecraft.item.ItemPickaxe implements ItemTool
{
    private final ContentItemPickaxe content;

    public ItemPickaxe(ToolMaterial material, ContentItemPickaxe content)
    {
        super(material);
        this.content = content;
    }

    @Override
    public void setDamage(float damage)
    {
        damageVsEntity = damage;
    }

    @Override
    public void setAttackSpeed(float attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.addAll(Arrays.asList(content.information));
    }
}
