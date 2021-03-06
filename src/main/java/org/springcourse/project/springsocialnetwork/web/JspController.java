package org.springcourse.project.springsocialnetwork.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springcourse.project.springsocialnetwork.dto.CommentDTO;
import org.springcourse.project.springsocialnetwork.model.Comment;
import org.springcourse.project.springsocialnetwork.model.Post;
import org.springcourse.project.springsocialnetwork.model.User;
import org.springcourse.project.springsocialnetwork.service.CommentService;
import org.springcourse.project.springsocialnetwork.service.FriendRequestService;
import org.springcourse.project.springsocialnetwork.service.PostService;
import org.springcourse.project.springsocialnetwork.service.SecurityService;
import org.springcourse.project.springsocialnetwork.service.UserService;
import org.springcourse.project.springsocialnetwork.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JspController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PostService service;
    
    @Autowired
    private FriendRequestService friendRequestSvc;
    
    @Autowired
    private CommentService commentService;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        if (securityService.getLoggedInName() != null) {
            final User user = userService.getLoggedUser();
            model.addAttribute("friends", user.getFriends());
            model.addAttribute("friendRequests", user.getFriendRequests());
            model.addAttribute("myRequests", friendRequestSvc.getAllFriendRequestsFrom());
            model.addAttribute("postForm", new Post());
            List<Post> feed = service.getFeed();
            List<CommentDTO> addComments = new ArrayList<>();
            for (int i = 0; i < feed.size(); i++) {
                addComments.add(new CommentDTO());
                model.addAttribute("commentForm_" + i, addComments.get(i));
            }
            model.addAttribute("postFeed", feed);
            
            return "index";
        }
        return "login";
    }
    
    @GetMapping(value = "/client")
    public String client(Model model) {
        return "client";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }
    
    @GetMapping(value = "/searchResults")
    public String searchResults(@RequestParam("searchName") String name, Model model) {
        final User user = userService.getLoggedUser();
        model.addAttribute("friends", user.getFriends());
        model.addAttribute("friendRequests", user.getFriendRequests());
        model.addAttribute("myRequests", friendRequestSvc.getAllFriendRequestsFrom());
        model.addAttribute("searchFriendsResults", userService.findByNonFriends(name));

        return "searchResults";
    }

    @PostMapping(value = "/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.createUser(userForm);

        securityService.login(userForm.getName(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @PostMapping(value = "/post")
    public String createPost(@ModelAttribute("postForm") Post postForm, BindingResult bindingResult, Model model) {
        final User user = userService.getLoggedUser();
        postForm.setUser(user);
        service.createPost(postForm);
        return "redirect:/";
    }

    @PostMapping(value="/comment")
    public String createComment(@ModelAttribute("commentForm") CommentDTO commentDTO, BindingResult bindingReuslt, Model model) {
        commentService.createComment(commentDTO.getText(), commentDTO.getPostId());
        return "redirect:/";
    }

    @PostMapping(value = "/searchResults")
    public String searchFriends(@RequestParam("searchName") String name) {
    	return "redirect:/searchResults?name=" + name;
    }

    @PostMapping(value = "/sendFriendRequest")
    public String sendFriendRequest(@RequestParam("user") String userName) {
    	friendRequestSvc.sendFriendRequest(userName);
    	return "redirect:/";
    }

    @PostMapping(value = "/acceptFriendRequest")
    public String acceptFriendRequest(@RequestParam("user") String userName) {
        friendRequestSvc.acceptFriendRequest(userName);
        return "redirect:/";
    }

    @PostMapping(value = "/declineFriendRequest")
    public String declineFriendRequest(@RequestParam("user") String userName) {
        friendRequestSvc.declineFriendRequest(userName);
        return "redirect:/";
    }

    @PostMapping(value = "/cancelFriendRequest")
    public String cancelFriendRequest(@RequestParam("user") String userName) {
        friendRequestSvc.cancelFriendRequest(userName);
        return "redirect:/";
    }
}
