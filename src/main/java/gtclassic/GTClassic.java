package gtclassic;

import gtclassic.commands.GTCommandTeleport;
import gtclassic.proxy.GTProxyCommon;
import gtclassic.util.*;
import gtclassic.world.GTOreGen;
import ic2.api.classic.addon.IC2Plugin;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

//@IC2Plugin(name = GTClassic.MODNAME, id = GTClassic.MODID, version = GTClassic.MODVERSION, hasResourcePack = true)
@Mod(modid = GTClassic.MODID, name = GTClassic.MODNAME, version = GTClassic.MODVERSION, dependencies = GTClassic.DEPENDS, useMetadata = true)

public class GTClassic {

    public static final String MODID = "gtclassic";
    public static final String MODNAME = "GregTech Classic";
    public static final String MODVERSION= "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod;required-after:forge@[14.23.5.2772,)";
    public static final CreativeTabs creativeTabGT = new GTCreativeTab(MODID);
    @SidedProxy(clientSide = "gtclassic.proxy.GTProxyClient", serverSide = "gtclassic.proxy.GTProxyServer")
    public static GTProxyCommon proxy;

    @Mod.Instance
    public static GTClassic instance;
    public static Logger logger;

    public static String getVersion() {
        return MODVERSION;
    }

    /*-------------------------------------------------------------------------
    This whole method was copied from Railcraft and therefore is their property.
    https://github.com/Railcraft/Railcraft
    --------------------------------------------------------------------------*/
//    @Mod.EventHandler
//    public void fingerprintError(FMLFingerprintViolationEvent event) {
//        if (Game.isObfuscated()) {
//            Game.logErrorFingerprint(MODID);
////            FMLCommonHandler.instance().exitJava(1, false);
//            throw new RuntimeException("Invalid Fingerprint");
//        }
//    }

    @Mod.EventHandler
    public synchronized void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        //GTIcons.loadSprites();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        GameRegistry.registerWorldGenerator(new GTOreGen(), 0);
        //GTBlocks.initHarvestLevel();
        GTRecipes.init();
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        // some hello world action
        logger.info("HELLO WORLD!");
        proxy.postInit(e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new GTCommandTeleport());
    }
}
