package com.truizlop.hiitwear.model;

import com.truizlop.hiitwear.R;

import java.util.Arrays;
import java.util.List;

public class HIIT {

    public static List<Exercise> getExercises(){
        return Arrays.asList(
                new Exercise(R.string.jumping_jacks, R.string.jumping_jacks_description, "https://www.youtube.com/watch?v=c4DAnQ6DtF8"),
                new Exercise(R.string.burpees, R.string.burpees_description, "https://www.youtube.com/watch?v=Uy2nUNX38xE"),
                new Exercise(R.string.squat, R.string.squat_description, "https://www.youtube.com/watch?v=p3g4wAsu0R4"),
                new Exercise(R.string.squat_jump, R.string.squat_jump_description, "https://www.youtube.com/watch?v=CVaEhXotL7M"),
                new Exercise(R.string.jumping_lunge, R.string.jumping_lunge_description, "https://www.youtube.com/watch?v=1ExU8445rbU"),
                new Exercise(R.string.step_up, R.string.step_up_description, "https://www.youtube.com/watch?v=l4AA5d5mInQ"),
                new Exercise(R.string.mountain_climber, R.string.mountain_climber_description, "https://www.youtube.com/watch?v=fBZHkGT0W5Y"),
                new Exercise(R.string.skipping, R.string.skipping_description, "https://www.youtube.com/watch?v=GIsphkD1Qxw")
        );
    }
}