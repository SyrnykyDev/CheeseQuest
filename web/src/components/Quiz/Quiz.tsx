import React from "react";
import BubbleWrapper from "@/components/BubbleWrapper/BubbleWrapper";
import styles from "./Quiz.module.scss";
import { questionTypes } from "@/components/EditQuiz/EditQuiz.container";

const testQuiz = [
  {
    title: "Test Quiz",
    description: "Some text to check design",
    image: null,
    type: "testQuestions",
    suggestedAnswers: [
      {
        text: "Test Quiz",
        value: "testQuiz",
      },
      {
        text: "Test Quiz2",
        value: "testQuiz2",
      },
      {
        text: "Test Quiz3",
        value: "testQuiz3",
      },
    ],
  },
];
const Quiz = () => {
  return (
    <div className={styles.quiz}>
      <BubbleWrapper centered={false}>test</BubbleWrapper>
      <BubbleWrapper centered={false}>test</BubbleWrapper>
      <BubbleWrapper centered={false}>test</BubbleWrapper>
    </div>
  );
};

export default Quiz;
