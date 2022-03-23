package com.fis.java.controller;

import com.fis.java.dao.BankAccountDAO;
import com.fis.java.exception.BankTransactionException;
import com.fis.java.form.SendMoneyForm;
import com.fis.java.model.BankAccountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class MainController {
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @GetMapping("/index")
    public String getIndex(Model model, @ModelAttribute("money")SendMoneyForm sendMoneyForm,
                           @RequestParam("message")Optional<String> message) {
        List<BankAccountInfo> list = bankAccountDAO.getBankAccounts();
        model.addAttribute("message", message.orElse(""));
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/send")
    public String send(@ModelAttribute("money") SendMoneyForm sendMoneyForm, RedirectAttributes redirectAttributes){
        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(),
                    sendMoneyForm.getToAccountId(),
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            log.error(e.getMessage());
            redirectAttributes.addAttribute("message", "Error: " + e.getMessage());
        }
        redirectAttributes.addAttribute("message", "Successfully Transaction");
        return "redirect:/index";
    }
}
