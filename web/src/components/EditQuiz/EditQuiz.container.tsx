"use client";

import { useEffect, useState } from "react";
import RandomNumber from "@/Functions/randomNumber";

export type questionTypes =
  | "openAnswers"
  | "testQuestions"
  | "imageObjectSearch";
interface IQuestion {
  title?: string;
  id: number;
  description?: string;
  timer?: number;
  position?: number;
  filled?: boolean;
  dirty?: boolean;
  type?: questionTypes;
}
const EditQuizContainer = () => {
  const [activeQuestion, setActiveQuestion] = useState<null | number>(null);
  const [questions, setQuestions] = useState<IQuestion[]>([
    // {
    //   id: 134,
    //   description: "tesst",
    //   title: "TitleTest",
    //   position: 1,
    // },
  ]);

  useEffect(() => {
    if (!questions.find((elem) => elem.id == activeQuestion))
      setActiveQuestion(null);
  }, [questions]);
  const onAddQuestion = () => {
    setQuestions([
      ...questions,
      {
        title: "",
        id: RandomNumber(),
        description: "",
        position: questions.length + 1,
      },
    ]);
  };

  const onSelectQuestion = (id: number) => {
    setActiveQuestion(id);
  };

  const onQuestionDelete = (id: number) => {
    if (id === activeQuestion) setActiveQuestion(null);
    setQuestions((prevState) => prevState.filter((elem) => elem.id != id));
    setQuestions((prevState) => {
      return prevState.map((elem, index) => {
        elem.position = index + 1;
        return elem;
      });
    });
  };

  const onQuestionChange = (key: keyof IQuestion, value: any) => {
    let editableQuestion = questions.find((elem) => elem.id == activeQuestion);
    editableQuestion = { ...editableQuestion, [key]: value };
    editableQuestion.dirty = true;
    if (editableQuestion?.description && editableQuestion?.title) {
      editableQuestion.filled = true;
    } else {
      editableQuestion.filled = false;
    }

    if (editableQuestion)
      setQuestions((prevState) => {
        let indexV;
        let newState2 = prevState;
        let newState = prevState.filter((elem, index) => {
          if (elem.id == activeQuestion) indexV = index;
          return elem.id != activeQuestion;
        });
        // newState2[indexV] = { ...editableQuestion };
        console.log("test", newState2);
        newState2[indexV] = editableQuestion;

        return [...newState2];
      });
  };

  const onQuestionTypeChange = (value: questionTypes) => {
    onQuestionChange("type", value);
  };

  return {
    functions: {
      onAddQuestion,
      onSelectQuestion,
      onQuestionDelete,
      onQuestionChange,
      onQuestionTypeChange,
    },
    states: { questions, activeQuestion },
  };
};

export default EditQuizContainer;
