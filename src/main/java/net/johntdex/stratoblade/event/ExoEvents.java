package net.johntdex.stratoblade.event;


import net.johntdex.stratoblade.StratoBlade;
import net.johntdex.stratoblade.item.ExoItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.SweepAttackEvent;

@EventBusSubscriber (modid = StratoBlade.MODID)
public class ExoEvents {

    @SubscribeEvent
    public static void onSweepAttack(SweepAttackEvent event) {
        if (event.getEntity().getMainHandItem().is(ExoItems.CARBON_STEEL_SCYTHE.get())) {
            event.setSweeping(true);
        }
    }
}
