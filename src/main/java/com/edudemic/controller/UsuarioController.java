package com.edudemic.controller;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.edudemic.entities.Usuario;
import com.edudemic.service.RolService;
import com.edudemic.service.UsuarioService;


@Controller
public class UsuarioController {
	
	private UsuarioService usuarioService;
	private RolService rolService;
	public UsuarioController(UsuarioService usuarioService,RolService rolService) 
	{
		this.usuarioService=usuarioService;
		this.rolService=rolService;
	}
	@GetMapping("/admin/registro")
	public String registrarAd(Model model) 
	{
		model.addAttribute("usuario",new Usuario());
		model.addAttribute("roles", rolService.listarRoles());
		return "registroA";
	}
	@PostMapping("/admins")
	public String registrarAdmin(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) 
	{
		try {
			if (result.hasErrors()) {
				return "registroA";
			}
			usuarioService.registrarUsuario(usuario);
			return "redirect:/admin/registro";
		} catch (Exception e) {
		}
		return "redirect:/admin/registro";
	}
}
