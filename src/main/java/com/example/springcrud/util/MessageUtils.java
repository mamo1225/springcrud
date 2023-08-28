package com.example.springcrud.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageUtils {

    /** メッセージソース */
    private static MessageSource messageSource;

    /** コンストラクタ */
    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * メッセージ取得
     *
     * @param key キー
     * @param args 引数
     * @return メッセージ
     */
    public static String getMesasge(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}
