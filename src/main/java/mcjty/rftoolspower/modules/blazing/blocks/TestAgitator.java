package mcjty.rftoolspower.modules.blazing.blocks;

import mcjty.rftoolspower.modules.blazing.items.BlazingRod;
import mcjty.rftoolspower.modules.blazing.items.IBlazingRod;

import java.util.function.Consumer;

import static mcjty.rftoolspower.modules.blazing.blocks.BlazingAgitatorTileEntity.BUFFER_SIZE;

public class TestAgitator {

    private static class DummyBlazingRod implements IBlazingRod {
        private float time = BlazingRod.MAXTIME;
        private float quality = BlazingRod.START_QUALITY;
        private float duration = BlazingRod.START_DURATION;

        public static IBlazingRod EMPTY = new DummyBlazingRod() {
            @Override
            public boolean isValid() {
                return false;
            }
        };

        public DummyBlazingRod() {
        }

        public DummyBlazingRod(float time, float quality, float duration) {
            this.time = time;
            this.quality = quality;
            this.duration = duration;
        }

        public DummyBlazingRod(IBlazingRod other) {
            this.time = other.getAgitationTimeLeft();
            this.quality = other.getPowerQuality();
            this.duration = other.getPowerDuration();
        }

        @Override
        public boolean isValid() {
            return true;
        }

        @Override
        public float getAgitationTimeLeft() {
            return time;
        }

        @Override
        public void setAgitationTimeLeft(float time) {
            this.time = time;
        }

        @Override
        public float getPowerQuality() {
            return quality;
        }

        @Override
        public void setPowerQuality(float quality) {
            this.quality = quality;
        }

        @Override
        public float getPowerDuration() {
            return duration;
        }

        @Override
        public void setPowerDuration(float duration) {
            this.duration = duration;
        }
    }

    private BlazingAgitatorAlgorithm algorithm;
    private IBlazingRod stacks[] = new DummyBlazingRod[BUFFER_SIZE];
    private boolean locked[] = new boolean[] {
            true, true, true,
            true, false, true,
            true, true, true
    };

    public TestAgitator() {
        algorithm = new BlazingAgitatorAlgorithm(slot -> stacks[slot]);
        for (int i = 0 ; i < stacks.length ; i++) {
            stacks[i] = DummyBlazingRod.EMPTY;
        }
    }

    public IBlazingRod tick() {
        for (int i = 0 ; i < BUFFER_SIZE ; i++) {
            IBlazingRod stack = stacks[i];
            if (stack.isValid()) {
                float timeLeft = stack.getAgitationTimeLeft();
                if (timeLeft > 0) {
                    algorithm.tickBlazingRod(i, stack, timeLeft, 1.0f);
                } else if (!locked[i]) {
                    return stack;
                }
            }
        }
        return null;
    }

    private void setStack(int slot, IBlazingRod stack) {
        stacks[slot] = stack;
    }

    private static IBlazingRod doTest(String header, Consumer<TestAgitator> setup) {
        TestAgitator agitator = new TestAgitator();
        setup.accept(agitator);
        IBlazingRod rod = agitator.tick();
        int ticks = 1;
        while (rod == null) {
            rod = agitator.tick();
            ticks++;
        }
        System.out.println("=== " + header + " ===");
        System.out.println("    ticks = " + ticks + ", seconds = " + (ticks / 20) + ", minutes = " + (ticks / 20 / 60));
        System.out.println("    timeLeft = " + rod.getAgitationTimeLeft());
        System.out.println("    quality = " + rod.getPowerQuality() + " (from " + BlazingRod.START_QUALITY + ")");
        System.out.println("    duration = " + rod.getPowerDuration() + " (from " + BlazingRod.START_DURATION + ")    -> total " + (rod.getPowerQuality() / 1000 * rod.getPowerDuration()) + " RF (" + (60*600) + " RF coalgen)");
        return rod;
    }

    public static void main(String[] args) {
        IBlazingRod fullTestRod = doTest("Full Test", a -> {
            for (int i = 0 ; i < BUFFER_SIZE ; i++) {
                a.setStack(i, new DummyBlazingRod());
            }
        });


        IBlazingRod rod = doTest("Rod Phase 1", a -> {
            a.setStack(4, new DummyBlazingRod());
        });
        for (int i = 2 ; i < 15 ; i++) {
            IBlazingRod finalRod = rod;
            IBlazingRod rodNew = doTest("Rod Phase " + i, a -> {
                a.setStack(4, new DummyBlazingRod());
                a.setStack(1, new DummyBlazingRod(finalRod));
                a.setStack(3, new DummyBlazingRod(finalRod));
                a.setStack(5, new DummyBlazingRod(finalRod));
                a.setStack(7, new DummyBlazingRod(finalRod));
            });
            rod = rodNew;
        }
    }

}
