"use client";
import React, { useState } from "react";
import BubbleWrapper from "@/components/BubbleWrapper/BubbleWrapper";
import styles from "./Profile.module.scss";
import Image from "next/image";
import Button from "@/components/Button/Button";

// *** ASSETS ***
import editSVG from "../../.././public/edit.svg";
import profileTest from "../../.././public/profileTest.jpg";
import QuizQuestion from "@/components/QuizQuestion/QuizQuestion";

const Page = () => {
  const isOwner = true;
  const [currentTab, setCurrentTab] = useState(0);

  const buttonArray = [
    { label: "Quests" },
    { label: "History" },
    { label: "Starred" },
  ];

  return (
    <BubbleWrapper>
      <div className={styles.profile}>
        <h1 style={{ display: "flex", gap: "15px", alignItems: "center" }}>
          <Image src={profileTest} alt={"Profile"} width={60} height={60} />
          DmytroQIvanov
          {isOwner && <Image src={editSVG} alt={"Edit user"} />}
        </h1>
        <div style={{ display: "flex", gap: "10px" }}>
          {buttonArray.map((elem, index) => (
            <Button
              key={index}
              buttonType={"round"}
              onClick={() => setCurrentTab(index)}
              active={currentTab == index}
            >
              {elem.label}
            </Button>
          ))}
        </div>
        <div className={styles.profile_container}>
          <QuizQuestion title={"Test"} id={0} />
          <QuizQuestion title={"Test"} id={0} />
          <QuizQuestion title={"Test"} id={0} />
          <QuizQuestion title={"Test"} id={0} />
          <QuizQuestion title={"Test"} id={0} />
          <QuizQuestion title={"Test"} id={0} />
        </div>
      </div>
    </BubbleWrapper>
  );
};

export default Page;
