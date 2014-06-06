package framework.impl;

import framework.AgentJoining;
import framework.Tick;

public class AgentJoining_impl<Context, Actionable> extends AgentJoining<Context, Actionable> {

	@Override
	protected JoiningEntity<Context, Actionable> make_JoiningEntity() {
		return new JoiningEntity<Context, Actionable>() {

			@Override
			protected Tick make_joinTick() {
				return eco_requires().universalTick();//Hello().helloWorld();
			}

			@Override
			protected Context make_joinContext() {
				return eco_requires().universalContext();
			}

			@Override
			protected Actionable make_joinActionable() {
				return eco_requires().universalActionable();
			}
			
		};
	}

}
