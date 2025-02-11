"use client";
import React from "react";

// *** STYLES ***
import styles from "./EditQuiz.module.scss";
import QuizQuestion from "@/components/QuizQuestion/QuizQuestion";

// *** ASSETS ***
import addCircleSVG from "../../../public/add-circle.svg";
import Image from "next/image";
import EditQuizContainer from "@/components/EditQuiz/EditQuiz.container";
import Input from "@/components/Input/Input";
import MotionWrapper from "@/components/MotionWrapper/MotionWrapper";
import Button from "@/components/Button/Button";

interface IEditQuizProps {
  type?: "edit" | "create";
}
const EditQuiz = ({ type }: IEditQuizProps) => {
  const {
    functions: {
      onAddQuestion,
      onSelectQuestion,
      onQuestionDelete,
      onQuestionChange,
      onQuestionTypeChange,
    },
    states: { questions, activeQuestion },
  } = EditQuizContainer();
  return (
    <div className={styles.editQuiz}>
      <div className={styles.editQuiz_questions}>
        <div
          style={{
            display: "flex",
            fontSize: "20px",
            alignItems: "center",
            justifyContent: "space-between",
          }}
        >
          <span style={{ marginBottom: "10px" }}>Questions</span>
          <div
            onClick={() => {
              onAddQuestion();
            }}
            style={{ cursor: "pointer" }}
          >
            <Image src={addCircleSVG} alt={"Add"} width={40} />
          </div>
        </div>
        <div className={styles.editQuiz_questions_container}>
          {questions.map((elem, index) => (
            <div onClick={() => onSelectQuestion(elem.id)} key={elem.id}>
              {/*{elem.id}*/}
              <MotionWrapper>
                <QuizQuestion
                  editable={true}
                  {...elem}
                  active={elem.id == activeQuestion}
                  onQuestionDelete={onQuestionDelete}
                />
              </MotionWrapper>
            </div>
          ))}
        </div>
      </div>
      {activeQuestion != null &&
        questions.find((elem) => elem.id == activeQuestion) && (
          <div className={styles.editQuiz_params}>
            <Input
              placeholder={"Enter the quest name"}
              inputType={"round"}
              required
              value={questions.find((elem) => elem.id == activeQuestion)?.title}
              onChange={(event) => {
                onQuestionChange("title", event.target.value);
              }}
            />
            <Input
              placeholder={"Enter the description"}
              required
              value={
                questions.find((elem) => elem.id == activeQuestion)?.description
              }
              inputType={"round"}
              onChange={(event) => {
                onQuestionChange("description", event.target.value);
              }}
            />
            <div style={{ display: "flex", gap: "10px" }}>
              <Input
                type={"number"}
                placeholder={"Enter a time limit (mins)"}
                inputType={"round"}
                value={
                  questions.find((elem) => elem.id == activeQuestion)?.timer
                }
                onChange={(event) => {
                  onQuestionChange("timer", event.target.value);
                }}
              />
              {/*<Input*/}
              {/*  type={"number"}*/}
              {/*  placeholder={"Enter a time limit (mins)"}*/}
              {/*  inputType={"round"}*/}
              {/*/>*/}
            </div>
            <span style={{ fontSize: "18px" }}>Select Quest Type:</span>
            <div style={{ display: "flex", gap: "15px" }}>
              <Button
                buttonType={"round"}
                onClick={() => onQuestionTypeChange("openAnswers")}
                active={
                  questions.find((elem) => elem.id == activeQuestion)?.type ===
                  "openAnswers"
                }
              >
                Open answers
              </Button>
              <Button
                buttonType={"round"}
                onClick={() => onQuestionTypeChange("testQuestions")}
                active={
                  questions.find((elem) => elem.id == activeQuestion)?.type ===
                  "testQuestions"
                }
              >
                Test questions
              </Button>
              <Button
                buttonType={"round"}
                onClick={() => onQuestionTypeChange("imageObjectSearch")}
                active={
                  questions.find((elem) => elem.id == activeQuestion)?.type ===
                  "imageObjectSearch"
                }
              >
                Search for objects in the image
              </Button>
            </div>
            <div style={{ margin: "auto 10px auto auto" }}>
              <Button>{type == "edit" ? "Edit" : "Create"}</Button>
            </div>
          </div>
        )}
    </div>
  );
};

export default EditQuiz;
